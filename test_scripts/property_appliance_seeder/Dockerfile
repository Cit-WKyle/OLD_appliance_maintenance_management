FROM python:2

# Bundle app source
ADD ./src /src

RUN pip install -r /src/requirements.txt

# Run
CMD ["python", "/src/main.py"]
