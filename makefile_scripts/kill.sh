#!/bin/bash

jar_file_path=$1
kill -9 $(ps aux | grep "$jar_file_path" | awk '{print $2}')