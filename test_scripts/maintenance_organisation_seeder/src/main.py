
from boto import dynamodb2
from boto.dynamodb2.table import Table
from boto.dynamodb2.items import Item
from boto.dynamodb2.exceptions import ConditionalCheckFailedException

from aws_constants import access_key, secret_key, region, dynamodb_main_org_table, dynamodb_diag_rep_table
from random import randint

next_id = 1

engineer_ids = [11, 12, 13, 14, 15]

def dynamo_main():
    conn= dynamodb2.connect_to_region(region_name=region,aws_access_key_id=access_key,aws_secret_access_key=secret_key)
    diag_rep_table = Table(dynamodb_diag_rep_table, connection=conn)
    table = Table(dynamodb_main_org_table, connection=conn)

    diag_rep_res = diag_rep_table.scan()

    diag_rep_ids = []

    for diag_rep in diag_rep_res:
        diag_rep_ids.append(dict(diag_rep.get_raw_keys())['diagRepId']['S'])

    with table.batch_write() as batch:
        for i in range(0, 10):
            main_org = {}
            global next_id
            main_org['orgId'] = str(next_id)
            next_id+= 1
            engineers = []
            engineers.append(engineer_ids[0])
            engineers.append(engineer_ids[1])
            main_org['engineers'] = engineers
            pending = []
            pending.append(diag_rep_ids[randint(0, len(diag_rep_ids) - 1)])
            pending.append(diag_rep_ids[randint(0, len(diag_rep_ids) - 1)])
            resp = []
            resp.append(diag_rep_ids[randint(0, len(diag_rep_ids) - 1)])
            resp.append(diag_rep_ids[randint(0, len(diag_rep_ids) - 1)])
            main_org['pendingDiagnosticReports'] = pending
            main_org['respondedDiagnosticReports'] = resp

dynamo_main()
print 'Data seeded successfully'
