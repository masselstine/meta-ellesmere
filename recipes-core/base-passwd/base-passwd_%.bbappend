do_compile:prepend() {
    sed -i '/^kmem.*/a wheel:*:16:' ${S}/group.master
}
