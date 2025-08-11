extract text
ffmpeg -i dashcam.mp4 \
  -vf "crop=310:50:115:in_h+100,format=gray,eq=contrast=1.5:brightness=0.02,fps=1/5" \
  frames/frame_%04d.png

preview:
ffplay -i dashcam.mp4 -vf "crop=310:50:115:in_h+100,format=gray,eq=contrast=1.5:brightness=0.02,fps=1/5" 
