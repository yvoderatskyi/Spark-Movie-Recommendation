package repository

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{StringColumn, IntColumn}
import com.websudos.phantom.keys.PartitionKey
import models.User

class Users extends CassandraTable[Users, User] {
  override def tableName: String = "users"

  object userId extends IntColumn(this) with PartitionKey[Int] {
    override def name: String = "user_id"
  }
  object age extends IntColumn(this)
  object gender extends StringColumn(this)
  object occupation extends StringColumn(this)
  object zipCode extends StringColumn(this) {
    override def name: String = "zip_code"
  }

  override def fromRow(row: Row): User = {
    User(
      userId(row),
      age(row),
      gender(row),
      occupation(row),
      zipCode(row))
  }
}
