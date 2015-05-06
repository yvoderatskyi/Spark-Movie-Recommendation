package models

case class Movie(
  id: Int,
  title: String,
  prodDate: String,
  link: String
) extends Serializable

object Movie {
  def apply(input: String): Movie = input.split("\\|+").take(4) match {
    case Array(id, title, date, link) => Movie(id.toInt, title, date, link)
  }
}
