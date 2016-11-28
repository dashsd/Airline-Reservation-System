package com.napier.airlinereservation.helpers;

import com.napier.airlinereservation.helpers.DataHelper.OpType;

public interface IObserver<T> {
	void notify(T model, OpType opType);
}
