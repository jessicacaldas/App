package estg.ipvc.app.entities

import androidx.room.Entity

@Entity(tableName = "nota_table")
class Nota(@PrimaryKey @ColumnInfo(name = "nota") val nota: String)


class Nota {
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "nota") val nota: String,
    @ColumnInfo(name = "descicao") val descricao: String
                    

}


