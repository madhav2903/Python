FROM python:3.8
RUN mkdir src
COPY requirements.txt src
COPY .vscode src/.vscode
COPY core src/core
COPY .env src
COPY manage.py src
WORKDIR src
RUN pip install -r requirements.txt
EXPOSE 8086
CMD ["python", "./test.py"]

