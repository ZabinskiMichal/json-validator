# JSON Validator

## About
This tool checks if JSON files, especially AWS IAM Role Policy files, follow specific rules. It makes sure the `Resource` field doesn't just have a "*" which can be too open.

## Quick Start

You need Java installed to run this tool. You can download the `.jar
` file from the target folder.

### Run It
After downloading `json-validator-1.0-SNAPSHOT.jar
`, open a terminal and run:

```bash
java -jar json-validator.jar /path/to/yourfile.json
