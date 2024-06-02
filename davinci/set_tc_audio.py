from datetime import datetime

resolve = resolve.GetProjectManager()
currentPool = resolve.GetCurrentProject().GetMediaPool()
clips = currentPool.GetRootFolder().GetClipList()

for clip in clips:
    if not(clip.GetName().endswith(".WAV")):
        continue
    print(clip.GetName())
    date_str = clip.GetClipProperty("Date Modified")
    print(date_str)
    time_object = datetime.strptime(date_str, '%a %b %d %H:%M:%S %Y').time()
    clip.SetClipProperty("Start TC", time_object.strftime("%H:%M:%S:00"))
