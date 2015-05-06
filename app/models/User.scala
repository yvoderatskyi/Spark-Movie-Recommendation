package models

case class User(
  id: Int,
  age: Int,
  gender: String,
  occupation: String,
  zipCode: String
) extends Serializable

object User extends Serializable {
  def apply(input: String): User = input.split("\\|+") match {
    case Array(id, age, gender, occ, zip) =>
      User(
        id.toInt,
        age.toInt,
        gender,
        occ,
        zip)
  }
}
