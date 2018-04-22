package com.kgribov.telegram.service

import scalikejdbc.ConnectionPool

object DatabaseConnector {

  def connectToDB(): Unit = {
    Class.forName("org.postgresql.Driver")
    ConnectionPool.singleton("jdbc:postgresql://database/postgres", "postgres", "postgres")
  }
}
