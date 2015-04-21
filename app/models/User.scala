package models

case class User(
  id: Int,
  age: Short,
  gender: String,
  occupation: String,
  zipCode: String
) extends Serializable

object User extends Serializable {
  def fromString(input: String) = input.split("\\|+") match {
    case Array(id, age, gender, occ, zip) =>
      User(
        id.toInt,
        age.toShort,
        gender,
        occ,
        zip)
  }
}
