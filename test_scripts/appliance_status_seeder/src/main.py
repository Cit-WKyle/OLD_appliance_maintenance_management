import json
from boto import dynamodb2
from boto.dynamodb2.table import Table
from boto.dynamodb2.items import Item
from boto.dynamodb2.exceptions import ConditionalCheckFailedException
from aws_constants import access_key, secret_key, region, dynamodb_appliance_status_table

next_id = 1

common = [
    {'type': 'OKAY', 'applianceType': 'COMMON', 'message': 'Message explaining status'},
    {'type': 'IRREPAIRABLE', 'applianceType': 'COMMON', 'message': 'Message explaining status'},
    {'type': 'REPAIRING', 'applianceType': 'COMMON', 'message': 'Message explaining status'}
]

def load_stat_icon():
    data_dir = '/src/dummy_data/'
    stat_icon = json.loads(open(data_dir + 'StatusIcon.json').read())
    return stat_icon

def load_appliance_statuses():
    data_dir = '/src/dummy_data/'
    appliance_statuses = json.loads(open(data_dir + 'ApplianceStatus.json').read())
    return appliance_statuses + common

def dynamo_main(appliance_statuses, stat_icon):
    conn= dynamodb2.connect_to_region(region_name=region,aws_access_key_id=access_key,aws_secret_access_key=secret_key)
    table = Table(dynamodb_appliance_status_table, connection=conn)

    with table.batch_write() as batch:
        for appliance_stat in appliance_statuses:
            global next_id
            appliance_stat['statusId'] = str(next_id)
            appliance_stat['icon'] = stat_icon
            next_id+= 1
            batch.put_item(data=appliance_stat)

appliance_statuses = load_appliance_statuses()
stat_icon = load_stat_icon()
dynamo_main(appliance_statuses, stat_icon)
print 'Data seeded successfully'
