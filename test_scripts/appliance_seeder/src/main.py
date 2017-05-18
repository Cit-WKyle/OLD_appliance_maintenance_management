import json
from decimal import *
from boto import dynamodb2
from boto.dynamodb2.table import Table
from boto.dynamodb2.items import Item
from boto.dynamodb2.exceptions import ConditionalCheckFailedException

from aws_constants import access_key, secret_key, region, dynamodb_appliance_table

next_id = 1

def update_appliance_image(appliance, appliance_image):
    global next_id
    appliance['applId'] = str(next_id)
    appliance['applianceImage'] = appliance_image
    next_id += 1
    return appliance

def load_appliances():
    data_dir = '/src/dummy_data/'
    appliance_image = json.loads(open(data_dir + 'ApplianceImage.json').read())

    dish_washer = json.loads(open(data_dir + 'DishWasher.json').read())
    fridge_freezer = json.loads(open(data_dir + 'FridgeFreezer.json').read())
    microwave = json.loads(open(data_dir + 'Microwave.json').read())
    oven = json.loads(open(data_dir + 'Oven.json').read())
    tumble_dryer = json.loads(open(data_dir + 'TumbleDryer.json').read())
    washing_machine = json.loads(open(data_dir + 'WashingMachine.json').read())

    appliances = dish_washer + fridge_freezer + microwave + oven + tumble_dryer + washing_machine

    return [update_appliance_image(appliance, appliance_image) for appliance in appliances]

def dynamo_main(appliances):
    conn= dynamodb2.connect_to_region(region_name=region,aws_access_key_id=access_key,aws_secret_access_key=secret_key)
    table = Table(dynamodb_appliance_table, connection=conn);

    with table.batch_write() as batch:
        for appliance in appliances:
            batch.put_item(data=appliance)

appliances = load_appliances()
dynamo_main(appliances)
print 'Data seeded successfully'
