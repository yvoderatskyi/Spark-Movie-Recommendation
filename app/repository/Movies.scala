package repository

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{StringColumn, IntColumn}
import com.websudos.phantom.keys.PartitionKey
import models.Movie

class Movies extends CassandraTable[Movies, Movie] {
  override def tableName: String = "movies"

  object id extends IntColumn(this) with PartitionKey[Int]
  object title extends StringColumn(this)
  object prodDate extends StringColumn(this)
  object link extends StringColumn(this)

  override def fromRow(row: Row): Movie = {
    Movie(
      id(row),
      title(row),
      prodDate(row),
      link(row))
  }
}
