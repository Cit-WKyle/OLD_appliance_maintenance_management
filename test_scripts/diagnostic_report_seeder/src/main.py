'''
1. id field - diagRepId
2. connect and get property appliance id
3. list containing manager ids
4. generate timestamp
5. description
6. empty list for organisations
'''
from boto import dynamodb2
from boto.dynamodb2.table import Table
from boto.dynamodb2.items import Item
from boto.dynamodb2.exceptions import ConditionalCheckFailedException
from random import randint
import time
import decimal

from aws_constants import access_key, secret_key, region, dynamodb_prop_appl_table, dynamodb_diag_rep_table

next_id = 1

manager_ids = [1, 2, 3, 4, 5]

def dynamo_main():
    conn= dynamodb2.connect_to_region(region_name=region,aws_access_key_id=access_key,aws_secret_access_key=secret_key)
    prop_appl_table = Table(dynamodb_prop_appl_table, connection=conn)
    table = Table(dynamodb_diag_rep_table, connection=conn)

    prop_appl_res = prop_appl_table.scan()

    prop_appl_ids = []

    for prop_appl in prop_appl_res:
        prop_appl_ids.append(dict(prop_appl.get_raw_keys())['propApplId']['S'])

    with table.batch_write() as batch:
        for i in range(0, 20):
            diag_rep = {}
            global next_id
            diag_rep['diagRepId'] = str(next_id)
            next_id+= 1
            diag_rep['propApplId'] = prop_appl_ids[randint(0, len(prop_appl_ids) - 1)]
            diag_rep['managerId'] = manager_ids[randint(0, len(manager_ids) - 1)]
            diag_rep['organisations'] = ["1"]
            diag_rep['timestamp'] = int(time.time())
            diag_rep['description'] = "Description"
            batch.put_item(data=diag_rep)

dynamo_main()
print 'Data seeded successfully'
