package com.example.cashtrack.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "finance_app.db"
        private const val DATABASE_VERSION = 1

        // Tables
        const val TABLE_TRANSACTIONS = "transactions"
        const val TABLE_SAVINGS_GOALS = "savings_goals"
        const val TABLE_EMPLOYEES = "employees"
        const val TABLE_PAYROLL = "payroll"

        // Common columns
        const val COLUMN_ID = "id"
        const val COLUMN_CREATED_AT = "created_at"

        // Transaction columns
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_AMOUNT = "amount"
        const val COLUMN_CATEGORY = "category"

        // Savings goal columns
        const val COLUMN_GOAL_NAME = "goal_name"
        const val COLUMN_TARGET_AMOUNT = "target_amount"
        const val COLUMN_CURRENT_AMOUNT = "current_amount"

        // Employee columns
        const val COLUMN_EMPLOYEE_NAME = "employee_name"
        const val COLUMN_POSITION = "position"
        const val COLUMN_SALARY = "salary"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create transactions table
        val createTransactionsTable = """
            CREATE TABLE $TABLE_TRANSACTIONS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DESCRIPTION TEXT NOT NULL,
                $COLUMN_AMOUNT REAL NOT NULL,
                $COLUMN_CATEGORY TEXT NOT NULL,
                $COLUMN_CREATED_AT INTEGER NOT NULL
            )
        """.trimIndent()

        // Create savings goals table
        val createSavingsGoalsTable = """
            CREATE TABLE $TABLE_SAVINGS_GOALS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_GOAL_NAME TEXT NOT NULL,
                $COLUMN_TARGET_AMOUNT REAL NOT NULL,
                $COLUMN_CURRENT_AMOUNT REAL DEFAULT 0,
                $COLUMN_CREATED_AT INTEGER NOT NULL
            )
        """.trimIndent()

        // Create employees table
        val createEmployeesTable = """
            CREATE TABLE $TABLE_EMPLOYEES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMPLOYEE_NAME TEXT NOT NULL,
                $COLUMN_POSITION TEXT NOT NULL,
                $COLUMN_SALARY REAL NOT NULL,
                $COLUMN_EMAIL TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_CREATED_AT INTEGER NOT NULL
            )
        """.trimIndent()

        // Create payroll table
        val createPayrollTable = """
            CREATE TABLE $TABLE_PAYROLL (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                employee_id INTEGER NOT NULL,
                pay_period_start INTEGER NOT NULL,
                pay_period_end INTEGER NOT NULL,
                gross_pay REAL NOT NULL,
                tax_deductions REAL NOT NULL,
                net_pay REAL NOT NULL,
                paid_date INTEGER,
                FOREIGN KEY (employee_id) REFERENCES $TABLE_EMPLOYEES($COLUMN_ID)
            )
        """.trimIndent()

        db.execSQL(createTransactionsTable)
        db.execSQL(createSavingsGoalsTable)
        db.execSQL(createEmployeesTable)
        db.execSQL(createPayrollTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TRANSACTIONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SAVINGS_GOALS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EMPLOYEES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PAYROLL")
        onCreate(db)
    }
}