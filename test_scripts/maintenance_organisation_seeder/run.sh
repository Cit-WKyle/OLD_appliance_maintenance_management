./mockaroo_downloader.sh

docker build -t main_org_seeder ./
docker run main_org_seeder
