class Expense {
  constructor(category, budgetAmount, expenseAmount = 0.0) {
    this.category = category;
    this.budgetAmount = budgetAmount;
    this.expenseAmount = expenseAmount;
  }
  getCategory() {
    return this.category;
  }
  getBudgetAmount() {
    return this.budgetAmount;
  }
  getExpenseAmount() {
    return this.expenseAmount;
  }
  setExpenseAmount(expenseAmount) {
    this.expenseAmount = expenseAmount;
  }
}

class Employee {
  constructor(name, department, salary = 0.0, expenses = null) {
    this.name = name;
    this.department = department;
    this.salary = salary;
    this.expenses = expenses;
  }
  setSalary(salary) {
    this.salary = salary;
  }
  setExpenses(expenses) {
    this.expenses = expenses;
  }
  getDepartment(){
    return this.department;
  }
  calculateSavings(expenses) {
    let totalExpenses = 0.0;
    console.log("{:<15} {:<15} {:<15} {:<15} {:<15}".format("Name", "Category", "Budget", "Expense", "Status"));
    console.log("---------------------------------------------------------------------------");
    console.log(expenses);
    for (let exp of expenses) {
      totalExpenses += exp.getExpenseAmount();
      let result = "Above";
      if (exp.getExpenseAmount() <= exp.getBudgetAmount()) {
        result = "Below";
      }
      console.log("{:<15} {:<15} {:<15} {:<15} {:<15}".format(this.name, exp.category, exp.getBudgetAmount(),
                                                              exp.getExpenseAmount(), result));
    }
    return this.salary - totalExpenses;
  }
}

function main() {
  let expenses = [];
  let employees = [];
  let noOfCat = 0;

  // Ask user for No of Category (min. 3)
  // Then get details of each expense category
  while (noOfCat < 3) {
    try {
      noOfCat = parseInt(prompt("Please enter No. of Expense Category (min. 3): "));
    } catch (e) {
      console.log("Please enter an integer.");
    }
  }

  for (let i = 0; i < noOfCat; i++) {
    console.log(`Enter details of expense category ${i + 1}`);
    let cat = prompt("Category - ");
    let bud = parseFloat(prompt("Budget - "));

    expenses.push(new Expense(cat, bud));
  }

  for (let ex of expenses) {
    console.log(`${ex.getCategory().padEnd(20)} budget:${ex.getBudgetAmount().toFixed(2).padStart(10)}`);
  }

  // Get employees details (min. 3) - Name, department, salary)
  // Get the expenses per category
  // Calculate saving of the employee
  let noOfEmployee = 0;

  while (noOfEmployee < 2) {
    try {
      noOfEmployee = parseInt(prompt("Please enter No. of Employees (min. 3): "));
    } catch (e) {
      console.log("Please enter an integer.");
    }
  }

  for (let i = 0; i < noOfEmployee; i++) {
    console.log(`Enter details of employee ${i + 1}`);
    let name = prompt("Name - ");
    let dept = prompt("Department - ");
    let salary = parseFloat(prompt("Salary - "));

    // Create obj and add the list of employees
    let thisEmp = new Employee(name, dept, salary, expenses);
    console.log(thisEmp.name);
    for (let ex of expenses) {
      let catNumber = parseFloat(prompt(`Expense Amount for category ${ex.category} - `));
      ex.setExpenseAmount(catNumber);
    }
    employees.push(thisEmp);
    console.log(employees);
  }
  for (let emp of employees) {
    let savings = emp.calculateSavings(emp.expenses);
    console.log(`Savings = ${savings.toFixed(2)}`);
    console.log("---------------------------------------------------------------------------");
  }
}

main();