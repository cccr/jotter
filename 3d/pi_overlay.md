sudo raspi-config

Navigate down to “Performance Options” and then “Overlay File System.” Select “Yes” to both the enable and write-protect questions.
It may take a minute or more while the system works, this is normal. Tab to the “Finish” button and reboot when prompted.

Restoring Read/Write Access
If you need to temporarily enable read/write access, as when editing tricky configuration settings in /boot/config.txt, or major system updates affecting the kernel or device tree files, this can be done from the command line (if using “full” Raspbian with a GUI, open a terminal window):

sudo mount -o remount,rw /boot

Perform your edits or upgrades, then reboot the system (via Pi→Shutdown with the GUI, or sudo reboot from the command line). It will be back in its read-only state.
If you need to permanently restore read/write access, you must first use the temporary step above, then can navigate to the same GUI or raspi-config settings, selecting “Read-write” for the GUI option, or “No” for the raspi-config write-protect question. Then reboot and the system’s back to normal, with permanent read/write access.

Now make an image of the SD card (using dd or Apple Pi Baker)
