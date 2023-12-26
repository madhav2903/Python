FROM python:3.8
RUN mkdir src
COPY requirements.txt src
COPY .vscode src src/.vscode
COPY core src/core
COPY .env src
COPY manage.py
WORKDIR src
RUN pip install -r requirements.txt
EXPOSE 8086
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8086"]
