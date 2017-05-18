'''
list of pending types
acc ids for pending 
acc ids for property
random property ids, refer to property-appliance
'''
from boto import dynamodb2
from boto.dynamodb2.table import Table
from boto.dynamodb2.items import Item
from boto.dynamodb2.exceptions import ConditionalCheckFailedException
from random import randint
from aws_constants import access_key, secret_key, region, dynamodb_property_table, dynamodb_pending_tenant_table, dynamodb_property_tenant_table

pend_next_id = 1
prop_next_id = 1

pending_types = ['APPROVED', 'PENDING', 'DECLINED']

pending_acc_ids = [7, 8]
property_acc_ids = [9, 10]

conn= dynamodb2.connect_to_region(region_name=region,aws_access_key_id=access_key,aws_secret_access_key=secret_key)

property_table = Table(dynamodb_property_table, connection=conn)
pend_tnt_table = Table(dynamodb_pending_tenant_table, connection=conn)
prop_tnt_table = Table(dynamodb_property_tenant_table, connection=conn)

properties = []

for prop in properties_res:
    properties.append(dict(prop.get_raw_keys())['id']['S'].encode('ascii'))

with pend_tnt_table.batch_write() as batch:
    for pend_acc_id in pending_acc_ids:
        # Insert ids here
        pending = {}
        pending['pendTntid'] = str(pend_next_id)
        pend_next_id+= 1
        pending['propertyId'] = properties[randint(0, len(properties) - 1)]
        pending['status'] = pending_types[randint(0, len(pending_types) - 1)]
        pending['accountId'] = pend_acc_id
        batch.put_item(data=pending)

with prop_tnt_table.batch_write() as batch:
    for property_acc_id in property_acc_ids
        prop = {}
        prop['propTntId'] = str(prop_next_id)
        prop_next_id+= 1
        prop['propertyId'] = properties[randint(0, len(properties) - 1)]
        prop['accountId'] = property_acc_id
        batch.put_item(data=prop)

print "Data seeded successfully"
