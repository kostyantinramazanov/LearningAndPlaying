import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BlockingQueue {
	
	private class Producer implements Runnable {

		private Queue<String> queue;

		public Producer(Queue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			int i = 1;
			while (true) {
				synchronized (queue) {
					queue.add(String.valueOf(i++));
					queue.notify();
				}
				if (i > 100)
					break;
			}
		}

	}

	private class Consumer implements Runnable {

		private Queue<String> queue;
		private volatile boolean stop;

		public void setStop(boolean stop) {
			synchronized (queue) {
				queue.notify();
				this.stop = stop;
			}

		}

		public Consumer(Queue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			try {

				while (!stop) {
					synchronized (queue) {
						if (queue.isEmpty())
							queue.wait();
						System.out.println(queue.poll());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("GOT STOP");
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BlockingQueue().run();
	}

	private void run() {
		Queue<String> queue = new LinkedList<String>();
		
		Thread prod = new Thread(new Producer(queue));
		Consumer cons = new Consumer(queue);
		Thread consthr = new Thread(cons);
		consthr.start();
		prod.start();

		
		
		try {
			prod.join();
		} catch 	(InterruptedException e) {
			e.printStackTrace();
		}
		cons.setStop(true);
		queue.add("STOOPPPP");
//		consthr.stop();
//		synchronized(queue){
//		queue.notify();
//		}
	}

}
