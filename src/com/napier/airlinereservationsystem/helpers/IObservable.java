package com.napier.airlinereservation.helpers;

public interface IObservable<T> {
	void addObserver(IObserver<T> observer);
	void removeObserver(IObserver<T> observer);
}
