package repository

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{DoubleColumn, IntColumn}
import com.websudos.phantom.keys.PartitionKey
import org.apache.spark.mllib.recommendation.Rating

class Ratings extends CassandraTable[Ratings, Rating] {
  override def tableName: String = "ratings"

  object userId extends IntColumn(this) with PartitionKey[Int] {
    override def name: String = "user_id"
  }
  object movieId extends IntColumn(this) with PartitionKey[Int] {
    override def name: String = "movie_id"
  }
  object rating extends DoubleColumn(this)

  override def fromRow(row: Row): Rating = {
    Rating(
      userId(row),
      movieId(row),
      rating(row))
  }
}
