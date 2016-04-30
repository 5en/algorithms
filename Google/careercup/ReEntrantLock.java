// Implement ReentrantLock using simple locks.

package com.htyleo.algorithms;

public class ReEntrantLock {
    /* hold count */
    private int     count;

    /* whether this lock is locked */
    private boolean locked;

    /* the thread that owns this lock */
    private Thread  thread;

    public synchronized void lock() throws InterruptedException {
        if (locked && Thread.currentThread() == thread) {
            count++;
            return;
        }

        while (locked) {
            this.wait();
        }

        locked = true;
        thread = Thread.currentThread();
        count++;
    }

    public synchronized void unlock() throws IllegalMonitorStateException {
        if (!locked) {
            return;
        }

        if (thread != Thread.currentThread()) {
            throw new IllegalMonitorStateException();
        }

        if (--count == 0) {
            locked = false;
            this.notifyAll();
        }
    }
}
