package models

case class Page[T](
  items: Seq[T],
  num: Int,
  size: Int,
  total: Int,
  prev: Boolean,
  next: Boolean)