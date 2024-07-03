# 添加规则

curl --location --request POST 'http://localhost:8080/drools/rule/add' \
--header 'User-Agent: apifox/1.0.0 (https://www.apifox.cn)' \
--header 'Content-Type: application/json' \
--data-raw '{
"ruleId": 1,
"kieBaseName": "kieBase01",
"kiePackageName": "rules.rule01",
"ruleContent": "package rules.rule01 \n global java.lang.StringBuilder resultInfo \n rule \"rule-01\" when $i: Integer() then resultInfo.append(drools.getRule().getPackageName()).append(\".\").append(drools.getRule().getName()).append(\"执行了，前端传递的参数:\").append($i); end"
}'

curl http://localhost:8080/drools/rule/fireRule\?kieBaseName\=kieBase01\&param\=1
rules.rule01.rule-01执行了，前端传递的参数:1%

# 修改规则
➜  ~ curl --location --request POST 'http://localhost:8080/drools/rule/update' \
--header 'User-Agent: apifox/1.0.0 (https://www.apifox.cn)' \
--header 'Content-Type: application/json' \
--data-raw '{
"ruleId": 1,
"kieBaseName": "kieBase01",
"kiePackageName": "rules.rule01",
"ruleContent": "package rules.rule01 \n global java.lang.StringBuilder resultInfo \n rule \"rule-01\" when $i: Integer(intValue() > 3) then resultInfo.append(drools.getRule().getPackageName()).append(\".\").append(drools.getRule().getName()).append(\"执行了，前端传递的参数:\").append($i); end"
}'

curl http://localhost:8080/drools/rule/fireRule\?kieBaseName\=kieBase01\&param\=1
curl http://localhost:8080/drools/rule/fireRule\?kieBaseName\=kieBase01\&param\=6
rules.rule01.rule-01执行了，前端传递的参数:6%

# 删除规则
curl --location --request POST 'http://localhost:8080/drools/rule/deleteRule?ruleId=1&ruleName=rule-01'
删除成功%

curl http://localhost:8080/drools/rule/fireRule\?kieBaseName\=kieBase01\&param\=6
curl http://localhost:8080/drools/rule/fireRule\?kieBaseName\=kieBase01\&param\=1

# 模拟多个kbase
curl --location --request POST 'http://localhost:8080/drools/rule/add' \
--header 'Content-Type: application/json' \
--data-raw '{
"ruleId": 1,
"kieBaseName": "kieBase01",
"kiePackageName": "rules.rule01",
"ruleContent": "package rules.rule01 \n global java.lang.StringBuilder resultInfo \n rule \"rule-01\" when $i: Integer() then resultInfo.append(drools.getRule().getPackageName()).append(\".\").append(drools.getRule().getName()).append(\"执行了，前端传递的参数:\").append($i); end"
}'
添加成功%

curl --location --request POST 'http://localhost:8080/drools/rule/add' \
--header 'Content-Type: application/json' \
--data-raw '{
"ruleId": 2,
"kieBaseName": "kieBase02",
"kiePackageName": "rules.rule02",
"ruleContent": "package rules.rule02 \n global java.lang.StringBuilder resultInfo \n rule \"rule-01\" when $i: Integer() then resultInfo.append(drools.getRule().getPackageName()).append(\".\").append(drools.getRule().getName()).append(\"执行了，前端传递的参数:\").append($i); end"
}'
添加成功%

curl http://localhost:8080/drools/rule/fireRule\?kieBaseName\=kieBase01\&param\=1
rules.rule01.rule-01执行了，前端传递的参数:1%
curl http://localhost:8080/drools/rule/fireRule\?kieBaseName\=kieBase02\&param\=1
rules.rule02.rule-01执行了，前端传递的参数:1%

