'''
1. Read Dummy Data
2. Retrieve Property & Appliance data from dynamo
3. Randomly retrieve required ids
4. Aggregate data
5. Connect to AWS
6. Batch save to DynamoDB
'''
import json
from boto import dynamodb2
from boto.dynamodb2.table import Table
from boto.dynamodb2.items import Item
from boto.dynamodb2.exceptions import ConditionalCheckFailedException
from random import randint
import decimal

from aws_constants import access_key, secret_key, region, dynamodb_property_table, dynamodb_appliance_table, dynamodb_prop_appl_table, dynamodb_appl_status_table

next_id = 1

class DecimalEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, decimal.Decimal):
            return float(o)
        return super(DecimalEncoder, self).default(o)

def load_property_appliances():
    data_dir = '/src/dummy_data/'

    property_appliances = json.loads(open(data_dir + 'PropertyAppliance.json').read())
    return property_appliances

def dynamo_main(property_appliances):
    conn= dynamodb2.connect_to_region(region_name=region,aws_access_key_id=access_key,aws_secret_access_key=secret_key)
    property_table = Table(dynamodb_property_table, connection=conn)
    appliance_table = Table(dynamodb_appliance_table, connection=conn)
    status_table = Table(dynamodb_appl_status_table, connection=conn)
    table = Table(dynamodb_prop_appl_table, connection=conn)

    properties_res = property_table.scan()
    appliance_res = appliance_table.scan()
    statuses_res = status_table.scan()

    properties = []
    appliances = []
    statuses = []

    for prop in properties_res:
        properties.append(dict(prop.get_raw_keys())['propId']['S'].encode('ascii'))

    for appl in appliance_res:
        appliances.append(dict(appl.get_raw_keys())['applId']['S'])

    for status in statuses_res:
        statuses.append(dict(status.get_raw_keys())['statusId']['S'])

    with table.batch_write() as batch:
        for property_appliance in property_appliances:
            global next_id
            # Insert ids here
            property_appliance['propApplId'] = str(next_id)
            next_id+= 1
            property_appliance['propertyId'] = properties[randint(0, len(properties) - 1)]
            property_appliance['applianceId'] = str(appliances[randint(0, len(appliances) - 1)])
            property_appliance['statusId'] = str(statuses[randint(0, len(statuses) - 1)])
            property_appliance['statusHistory'] = [
                {'statusId': str(statuses[randint(0, len(statuses) - 1)]), 'dateTime':1487611362},
                {'statusId': str(statuses[randint(0, len(statuses) - 1)]), 'dateTime':1487611362},
                {'statusId': str(statuses[randint(0, len(statuses) - 1)]), 'dateTime':1487611362}
            ]
            batch.put_item(data=property_appliance)

property_appliances = load_property_appliances()
dynamo_main(property_appliances)
print 'Data seeded successfully'
