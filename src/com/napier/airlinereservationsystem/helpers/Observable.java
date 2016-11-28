package com.napier.airlinereservation.helpers;

import java.util.ArrayList;

import com.napier.airlinereservation.helpers.DataHelper.OpType;

public class Observable<T> implements IObservable<T> {

	private ArrayList<IObserver<T>> observers = new ArrayList<>();

	public void addObserver(IObserver<T> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}

	public void removeObserver(IObserver<T> observer) {
		synchronized (observers) {
			observers.remove(observer);
		}
	}

	public void notifyObservers(final T t, OpType opType) {
		synchronized (observers) {
			for (IObserver<T> iObserver : observers) {
				iObserver.notify(t, opType);
			}
		}
	}

}
