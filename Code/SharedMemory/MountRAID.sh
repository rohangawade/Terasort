


#!/bin/env bash

mkdir -p /data
mdadm --create --verbose --level=0 /dev/md0 --name=DATA --raid-devices=2 /dev/nvme0n1 /dev/nvme1n1 
mdadm --wait /dev/md0
mkfs.ext4 /dev/md0
mdadm --detail --scan >> /etc/mdadm.conf
dracut -H -f /boot/initramfs-$(uname -r).img $(uname -r)
echo /dev/md0 /data ext4 defaults,nofail,noatime,discard 0 2 >> /etc/fstab
mount -a