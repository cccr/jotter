// Rename DSC_1234-12.jpg into DSC_123.JPG in a folder
for f in *.jpg; do mv "$f" "$(echo "$f" | sed -E "s/(DSC_[0-9]{4})(-.*).jpg/\1.JPG/")"; done;
