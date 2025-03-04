package com.example.eurofitappfinal.data

import com.example.eurofitappfinal.model.ScoringModel

object ScoringRepository {
    val scoringData = listOf(
        // Test Cooper - Mujeres 12 años
        ScoringModel("Test Cooper", 12, "Femenino", 1200.0, 1250.0, 2.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1250.0, 1300.0, 2.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1300.0, 1350.0, 3.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1350.0, 1400.0, 3.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1400.0, 1450.0, 4.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1450.0, 1500.0, 4.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1500.0, 1550.0, 5.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1550.0, 1600.0, 5.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1600.0, 1650.0, 6.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1650.0, 1700.0, 6.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1700.0, 1750.0, 7.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1750.0, 1800.0, 7.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1800.0, 1850.0, 8.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1850.0, 1900.0, 8.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1900.0, 1950.0, 9.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1950.0, 2000.0, 9.5),
        ScoringModel("Test Cooper", 12, "Femenino", 2000.0, 2050.0, 10.0),

        // Test Cooper - Hombres 12 años
        ScoringModel("Test Cooper", 12, "Masculino", 1400.0, 1450.0, 2.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1450.0, 1500.0, 2.5),
        ScoringModel("Test Cooper", 12, "Masculino", 1500.0, 1550.0, 3.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1550.0, 1600.0, 3.5),
        ScoringModel("Test Cooper", 12, "Masculino", 1600.0, 1650.0, 4.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1650.0, 1700.0, 4.5),
        ScoringModel("Test Cooper", 12, "Masculino", 1700.0, 1750.0, 5.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1750.0, 1800.0, 5.5),
        ScoringModel("Test Cooper", 12, "Masculino", 1800.0, 1850.0, 6.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1850.0, 1900.0, 6.5),
        ScoringModel("Test Cooper", 12, "Masculino", 1900.0, 1950.0, 7.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1950.0, 2000.0, 7.5),
        ScoringModel("Test Cooper", 12, "Masculino", 2000.0, 2050.0, 8.0),
        ScoringModel("Test Cooper", 12, "Masculino", 2050.0, 2100.0, 8.5),
        ScoringModel("Test Cooper", 12, "Masculino", 2100.0, 2150.0, 9.0),
        ScoringModel("Test Cooper", 12, "Masculino", 2150.0, 2200.0, 9.5),
        ScoringModel("Test Cooper", 12, "Masculino", 2200.0, 2250.0, 10.0),

        // Test Cooper - Mujeres (13 años)
        ScoringModel("Test Cooper", 13, "Femenino", 1300.0, 1350.0, 2.0),
        ScoringModel("Test Cooper", 13, "Femenino", 1350.0, 1400.0, 2.5),
        ScoringModel("Test Cooper", 13, "Femenino", 1400.0, 1450.0, 3.0),
        ScoringModel("Test Cooper", 13, "Femenino", 1450.0, 1500.0, 3.5),
        ScoringModel("Test Cooper", 13, "Femenino", 1500.0, 1550.0, 4.0),
        ScoringModel("Test Cooper", 13, "Femenino", 1550.0, 1600.0, 4.5),
        ScoringModel("Test Cooper", 13, "Femenino", 1600.0, 1650.0, 5.0),

        // Velocidad 5x10 - Mujeres (14 años)
        ScoringModel("Velocidad 5x10", 14, "Femenino", 11.0, 13.4, 10.0),
        ScoringModel("Velocidad 5x10", 14, "Femenino", 13.4, 13.6, 9.5),
        ScoringModel("Velocidad 5x10", 14, "Femenino", 13.6, 13.8, 9.0),
        ScoringModel("Velocidad 5x10", 14, "Femenino", 13.8, 14.0, 8.5),
        ScoringModel("Velocidad 5x10", 14, "Femenino", 14.0, 14.2, 8.0),
        ScoringModel("Velocidad 5x10", 14, "Femenino", 14.2, 14.4, 7.5),
        ScoringModel("Velocidad 5x10", 14, "Femenino", 14.4, 14.6, 7.0),
        ScoringModel("Velocidad 5x10", 14, "Femenino", 14.6, 14.8, 6.5),

        // Lanzamiento de balón - Mujeres
        ScoringModel("Lanzamiento de balón", 14, "Femenino", 3.80, 4.10, 2.0),
        ScoringModel("Lanzamiento de balón", 14, "Femenino", 4.10, 4.40, 2.5),
        ScoringModel("Lanzamiento de balón", 14, "Femenino", 4.40, 4.70, 3.0),
        ScoringModel("Lanzamiento de balón", 14, "Femenino", 4.70, 5.00, 3.5),
        ScoringModel("Lanzamiento de balón", 14, "Femenino", 5.00, 5.30, 4.0),
        ScoringModel("Lanzamiento de balón", 14, "Femenino", 5.30, 5.60, 4.5),
        ScoringModel("Lanzamiento de balón", 14, "Femenino", 5.60, 5.90, 5.0)
    )

    fun getScore(testName: String, age: Int, gender: String, userValue: Double): Double? {
        return scoringData.find {
            it.testName == testName &&
                    it.age == age &&
                    it.gender == gender &&
                    userValue in it.minValue..it.maxValue
        }?.score
    }
}
