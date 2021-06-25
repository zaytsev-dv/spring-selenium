#!/bin/bash
find /opt/webmonitoring/build/reports/tests/ -type f -mtime +1 -exec rm -f {} \;
find /opt/webmonitoring/build/logs/ -type f -mtime +7 -exec rm -f {} \;
