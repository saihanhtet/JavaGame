from typing import List
from dataclasses import dataclass


@dataclass
class Expense:
    category: str
    budget_amount: float
    expense_amount: float = 0.0

    def get_category(self):
        return self.category

    def get_budget_amount(self):
        return self.budget_amount

    def get_expense_amount(self):
        return self.expense_amount

    def set_expense_amount(self, expense_amount):
        self.expense_amount = expense_amount


@dataclass
class Employee:
    name: str
    department: str
    salary: float = 0.0
    expenses: List[Expense] = None

    def set_salary(self, salary):
        self.salary = salary

    def set_expenses(self, expenses):
        self.expenses = expenses

    def calculate_savings(self,expenses):
        total_expenses = 0.0
        print("{:<15} {:<15} {:<15} {:<15} {:<15}".format("Name", "Category", "Budget", "Expense", "Status"))
        print("---------------------------------------------------------------------------")
        print(expenses)
        for exp in expenses:
            total_expenses += exp.get_expense_amount()

            result = "Above" if exp.get_expense_amount() > exp.get_budget_amount() else "Below"
            print("{:<15} {:<15} {:<15} {:<15} {:<15}".format(self.name, exp.category, exp.get_budget_amount(),
                                                              exp.get_expense_amount(), result))
        return self.salary - total_expenses


def main():
    expenses = []
    employees = []

    no_of_cat = 0

    # Ask user for No of Category (min. 3)
    # Then get details of each expense category
    while no_of_cat < 3:
        try:
            no_of_cat = int(input("Please enter No. of Expense Category (min. 3): "))
        except ValueError:
            print("Please enter an integer.")

    for i in range(no_of_cat):
        print(f"Enter details of expense category {i+1}")
        cat = input("Category - ")
        bud = float(input("Budget - "))

        expenses.append(Expense(cat, bud))

    for ex in expenses:
        print(f"{ex.get_category():20s}   budget:{ex.get_budget_amount():10.2f}")

    # Get employees details (min. 3) - Name, department, salary)
    # Get the expenses per category
    # Calculate saving of the employee
    no_of_employees = 0

    while no_of_employees < 2:
        try:
            no_of_employees = int(input("Please enter No. of Employees (min. 3): "))
        except ValueError:
            print("Please enter an integer.")

    for i in range(no_of_employees):
        print(f"Enter details of employee {i+1}")
        name = input("Name - ")
        dept = input("Department - ")
        salary = float(input("Salary - "))

        # Create obj and add the list of employees
        this_emp = Employee(name, dept, salary, expenses)
        print(this_emp.name) # lance
        for ex in this_emp.expenses: # lance\s [  expense class 1, expense 2]
            cat_expense = float(input(f"Expense Amount for category {ex.get_category()} - "))
            ex.set_expense_amount(cat_expense)
        employees.append(this_emp)
        print(employees)

    for emp in employees:
        savings = emp.calculate_savings(expenses)
        print(f"Savings = {savings:.2f}")
        print("---------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
