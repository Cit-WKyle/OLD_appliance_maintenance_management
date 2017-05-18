./mockaroo_downloader.sh

docker build -t diag_rep_seeder ./
docker run diag_rep_seeder
