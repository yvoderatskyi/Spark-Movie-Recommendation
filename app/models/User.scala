package models

import services.SparkService

/**
 * Created by yuriy on 2/27/15.
 */

case class User(id: Int, age: Short, gender: String, occupation: String, zipCode: String) extends Serializable

object User extends Serializable {
  def fromString(input: String) = input.split("\\|+") match {
    case Array(id, age, gender, occ, zip) => User(id.toInt, age.toShort, gender, occ, zip)
  }

  lazy val users = SparkService.userRDD

  def listUsers: List[User] = {
    SparkService.userRDD
      .sortBy(u => u.id)
      .collect()
      .toList
  }

  def getUserById(id: Long): Option[User] = {
    SparkService.userRDD
      .collect()
      .toList
      .find(user => user.id == id)
  }
}
