package com.antonioajlb.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.antonioajlb.data.local.AppDatabase
import com.antonioajlb.data.repository.CategoryRepositoryImpl
import com.antonioajlb.data.repository.ProductRepositoryImpl
import com.antonioajlb.domain.irepository.CategoryRepository
import com.antonioajlb.domain.irepository.ProductRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "churreriadb"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
//                    db.execSQL(
//                        """
//        INSERT INTO product (name, price, type) VALUES
//        ('Churros de rueda', 3.00, 'CHURROS'),
//        ('Churros de papita', 3.00, 'CHURROS'),
//        ('Relleno Chocolate', 3.50, 'RELLENOS'),
//        ('Relleno Chocolate B.', 3.50, 'RELLENOS'),
//        ('Relleno Kinder', 3.50, 'RELLENOS'),
//        ('Relleno Pistacho', 3.50, 'RELLENOS'),
//        ('Relleno Lotus', 3.50, 'RELLENOS'),
//        ('Relleno Vainilla', 3.50, 'RELLENOS'),
//        ('CocaCola', 2.00, 'REFRESCOS'),
//        ('Fanta de Naranja', 2.00, 'REFRESCOS'),
//        ('Fanta de Limon', 2.00, 'REFRESCOS'),
//        ('CocaCola Cero', 2.00, 'REFRESCOS'),
//        ('Cerveza', 2.00, 'REFRESCOS'),
//        ('Maracuya', 2.00, 'REFRESCOS'),
//        ('Cafe Solo', 2.00, 'CAFES'),
//        ('Cafe con Leche', 2.00, 'CAFES'),
//        ('Cafe Manchado', 2.00, 'CAFES'),
//        ('Cafe Cortado', 2.00, 'CAFES'),
//        ('Descafeinado Sobre', 2.00, 'CAFES'),
//        ('Descafeinado con Leche', 2.00, 'CAFES'),
//        ('Descafeinado Manchado', 2.00, 'CAFES'),
//        ('Descafeinado Cortado', 2.00, 'CAFES'),
//        ('Batido Chocolate', 2.00, 'BEBIDAS'),
//        ('Batido Fresa', 2.00, 'BEBIDAS'),
//        ('Zumo Piña', 2.00, 'BEBIDAS'),
//        ('Zumo Melocotón', 2.00, 'BEBIDAS'),
//        ('Agua Pequeña', 1.50, 'BEBIDAS'),
//        ('Agua Grande', 2.00, 'BEBIDAS'),
//        ('Buñuelos', 4.00, 'BUNUELOS'),
//        ('Buñuelos 3S.', 4.50, 'BUNUELOS')
//    """.trimIndent()
//                    )
                }
            })
            .build()
    }

    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().categoryDao() }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}