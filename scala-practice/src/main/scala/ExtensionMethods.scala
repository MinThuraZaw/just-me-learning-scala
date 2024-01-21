class ExtensionMethods {

}

// extension not directly supported in scala 2
trait Ordering[A]{
  def compare(x: A, y: A): Int

}

// implicit conversion
sealed trait Json
case class JNumber(value: BigDecimal) extends Json
case class JString(value: String) extends Json
case class JBoolean(value: Boolean) extends Json
case class JArray(value: List[Json]) extends Json
case class JObject(fields: (String, Json)*) extends Json

object Json{
//  def obj(fields: (String, JsonField)* ): Json = {
//    // JObject(fields.map(_.json)*)
//
//  }

  case class JsonField(json: Json)
}