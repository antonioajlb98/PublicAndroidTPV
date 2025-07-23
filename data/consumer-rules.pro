# Evita advertencias de Room
-dontwarn androidx.room.**

# Mantén clases anotadas con Room
-keep class androidx.room.** { *; }

# Mantén tus DAOs, entidades, y base de datos
-keep class com.antonioajlb.data.local.** { *; }

# Mantén miembros con anotaciones de Room
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
}