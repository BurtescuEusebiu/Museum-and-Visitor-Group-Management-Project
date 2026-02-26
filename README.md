# Museum and Visitor Group Management Project

## **Design Patterns Used**
To structure the application efficiently, scalably, and maintainably, several established design patterns from software engineering were used. These patterns provide standardized solutions for common architectural problems and facilitate project development and extension without compromising code clarity. Below are the patterns applied, why they were chosen, and how they were integrated.

---

### **1. Command Pattern**
**Motivation:**  
The Command Pattern was used to separate the logic of different actions from the rest of the application, allowing commands to be treated as independent objects. This is especially useful when multiple types of operations need to be executed, saved, or undone.

**How it was used:**  
- Adding a museum, guide, or member: Each addition is treated as a separate command executed when needed.  
- Removing a guide or member: Deletion operations are encapsulated in independent commands.  
- Searching for a guide or member: Each search operation is handled as a command, providing flexibility in processing results.

**Benefits:**  
- Flexibility to add new actions without modifying the main logic.  
- Increased modularity and code reuse.

---

### **2. Builder Pattern**
**Motivation:**  
The Builder Pattern was used to manage the creation of complex objects with optional parameters. Instead of using overloaded constructors or multiple setter methods, Builder allows flexible and clear object construction. This is crucial for objects that can be configured in multiple ways but don’t require all attributes.

**How it was used:**  
- Applied to `Museum` and `Location` classes, which have optional parameters such as description, opening hours, and coordinates.  
- Objects are built step by step, adding only the necessary information.  
- Constructors remain simple, avoiding multiple combinations of parameters.

**Benefits:**  
- Clear and flexible object construction.  
- Ability to add new attributes without affecting existing code.  
- Reduced errors from using constructors with many parameters.  
- Supports creation of immutable objects, which are safer and easier to test.

---

### **3. Observer Pattern**
**Motivation:**  
The Observer Pattern was used to manage automatic notifications between entities. Museums act as subjects notifying registered guides when a new event or schedule change occurs. Guides are observers who receive these notifications to inform visitor groups.

**How it was used:**  
- `Museum` class acts as the subject maintaining a list of `registeredGuides`.  
- Whenever a museum announces a new event, all registered guides are automatically notified and can act accordingly.

**Benefits:**  
- Efficient automation of notifications without manual intervention.  
- Decouples museums from guides—museums do not need details about each guide.  
- Extensible: new observer types (e.g., mobile apps or services) can be added without changing museum logic.

---

### **4. Singleton Pattern**
**Motivation:**  
The Singleton Pattern ensures a single instance of the `Database` class across the entire application. This guarantees that all components access the same data source, preventing concurrency issues and resource mismanagement.

**How it was used:**  
- `Database` class, responsible for storing and managing museums, guides, and members, was implemented as a singleton.  
- All add, remove, and search operations use this single shared instance, avoiding conflicts or inconsistencies.

**Benefits:**  
- Centralized access and unified control over data.  
- Prevents accidental creation of multiple instances that could cause inconsistencies.  
- Simplifies database configuration and management.

---

## **Conclusion**
Using these design patterns contributed to a robust, easily extensible, and maintainable application. Each pattern solved a specific architectural problem, and together they enabled clear and efficient code organization. The application is ready to scale and adapt to future requirements without compromising code quality or clarity.
