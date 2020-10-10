# See PyCharm help at https://www.jetbrains.com/help/pycharm/
#!/usr/bin/python
import subprocess

from flask import Flask, request

app = Flask("my_app1")

@app.route("/rail")
def search_rail():
    start_destination = str(request.args.get('startStop'))
    start_time = str(request.args.get('startTime'))
    end_destination = str(request.args.get('endStop'))
    return subprocess.check_output(
        ["java", "-classpath", "/home/afeka/Desktop/sprint3/Rails_IL/bin", "Rails_IL/UserMain", start_destination, start_time,
         end_destination])