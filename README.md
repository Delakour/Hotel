# 🏨 Hotel Management System

A comprehensive Java-based hotel management system that handles room bookings, guest management, and generates detailed reports.

## 📋 What This System Does

This is a **complete hotel management solution** that allows you to:

- **Manage hotel rooms** with different levels (Simple, Normal, Suite)
- **Handle guest registrations** (regular and business guests)
- **Process room bookings** with date validation and availability checking
- **Generate detailed reports** on room occupancy and guest activity
- **Calculate pricing** with automatic business guest discounts

## 🏗️ System Architecture

The project follows a **layered architecture pattern**:

```txt
📁 src/
├── 📁 View/          # User Interface Layer
├── 📁 BL/            # Business Logic Layer  
├── 📁 Dal/           # Data Access Layer
└── 📁 Entetis/       # Entity Classes
```

### Core Components

- **Hotel Management**: Central system managing all operations
- **Room Management**: Handles room availability, levels, and floor assignments
- **Guest Management**: Manages guest profiles and visit history
- **Order Management**: Processes bookings and calculates pricing
- **Reports System**: Generates activity and occupancy reports

## 🚀 Key Features

### 🏠 Room Management

- **3 Room Levels**: Simple (₪700/night), Normal (+₪150), Suite (+₪500)
- **Multi-floor Support**: Configurable number of floors
- **Real-time Availability**: Automatic conflict detection for bookings

### 👥 Guest System

- **Regular Guests**: Standard pricing and services
- **Business Guests**: Automatic discount application
- **Visit Tracking**: Maintains guest visit history

### 📊 Smart Booking

- **Date Validation**: Prevents double-bookings
- **Flexible Search**: Find rooms by floor, level, or both
- **Alternative Suggestions**: Offers alternatives when preferred rooms unavailable

### 📈 Reporting

- Active/Inactive room reports
- Guest activity summaries  
- Booking history by date ranges
- Revenue calculations

## 🛠️ How to Run

1. **Compile the Java files**:

   ```bash
   javac -d bin src/**/*.java
   ```

2. **Run the application**:

   ```bash
   java -cp bin View.Main
   ```

3. **Follow the interactive menu** to:
   - Add new bookings
   - View hotel details
   - Generate reports
   - Manage guests and rooms

## 💡 Sample Usage

The system comes pre-loaded with test data:

- **5 sample guests** (including business guests)
- **12 rooms** across 4 floors with different levels
- **8 sample bookings** to demonstrate functionality

## 📁 Generated Reports

The system automatically creates reports in the `files/` directory:

- `Report Active Rooms.txt`
- `Report Inactive Rooms.txt`  
- `Report Orders by date.txt`
- `Report Orders by number of days.txt`
- `Report Active Customers.txt`

## 🎯 Perfect For

- **Learning Object-Oriented Programming** in Java
- **Understanding layered architecture** patterns
- **Exploring business logic** implementation
- **File I/O operations** and report generation
- **Date handling** and validation systems

## 🔧 Technical Highlights

- **Singleton Pattern**: Ensures single hotel instance
- **Factory Pattern**: Creates different guest types
- **Stream API**: Modern Java collection processing
- **Enum Usage**: Type-safe room level definitions
- **Serialization**: Data persistence capabilities
- **Input Validation**: Robust error handling

---

*This system demonstrates professional Java development practices with clean code architecture and comprehensive business logic implementation.*
