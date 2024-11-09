package com.mohammed.mosa.eg.dailylabor.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohammed.mosa.eg.dailylabor.data.converter.DateTypeConverter
import com.mohammed.mosa.eg.dailylabor.data.dao.LaborDao
import com.mohammed.mosa.eg.dailylabor.data.dao.PaymentDao
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.data.module.LaborWithPayments
import com.mohammed.mosa.eg.dailylabor.data.module.Payment


@Database(
    entities = [
        Labor::class,
        Payment::class,
               ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateTypeConverter::class)

abstract class AppDatabase : RoomDatabase(){
    abstract val laborDao: LaborDao
    abstract val paymentDao: PaymentDao

}