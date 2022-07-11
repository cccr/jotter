COUNT=0
while read -r line; do
  COUNT=$(($COUNT + 1))
  sed -r "s#\"field_name\": \"(.*?)\"#\"field_name\": \"FAKE_VALUE_$COUNT\"#g" <<< $line
done <./file.json
