package services

import models._

object UserService {
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
