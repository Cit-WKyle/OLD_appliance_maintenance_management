./mockaroo_downloader.sh

docker build -t appl_stat_seeder ./
docker run appl_stat_seeder
