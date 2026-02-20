# AI Coding Agent Instructions - R11 Binary Search Tree

## Project Overview
This is a **binary search tree (BST) implementation project** for an academic course (R11-01, semestre 2025.2). The project focuses on implementing core BST operations and advanced tree manipulation algorithms.

**Key constraint:** This is a **submission assignment** (matricula: 124211085). Only `SimpleBSTManipulationImpl.java` is allowed to be modified. All other interface implementations must remain immutable.

---

## Architecture & Core Components

### 1. **Interface Hierarchy**
- `BT<T>` (Binary Tree interface) - Base contract for all binary tree operations
  - Defines: `getRoot()`, `isEmpty()`, `height()`, `search()`, `insert()`, `remove()`, traversals (`preOrder()`, `order()`, `postOrder()`)
- `BST<T>` (extends `BT<T>`) - Binary Search Tree specific operations
  - Adds: `maximum()`, `minimum()`, `sucessor()`, `predecessor()`
- `SimpleBSTManipulation<T>` - Advanced BST analysis (see below)

### 2. **Core Implementation Classes**
- `BTNode<T>` - Generic binary tree node with data, left, right, parent pointers
  - **NIL nodes:** Empty nodes have `data == null` (used for tree boundaries)
  - **Equality:** Compares only data, not structure
- `BSTNode<T>` - Extends BTNode; uses **Builder pattern** for construction
  - Example: `new BSTNode.Builder<T>().data(val).left(l).right(r).parent(p).build()`
- `BSTImpl<T>` - Core BST implementation (read-only for assignments)
  - Uses recursive private methods (`busca()`, `inserir()`, `altura()`, etc.)
  - **Important:** Null elements are never inserted; empty slots use NIL nodes

### 3. **SimpleBSTManipulation Interface** (Only Modifiable Class)
Located in `SimpleBSTManipulationImpl.java` - implement these three methods:

1. **`equals(BST<T> tree1, BST<T> tree2)`** - Structural & value equality
   - Must be implemented with **recursion only**
   - Compare nodes from roots; NIL == NIL, data equality required, structure must match
   - Use pattern: check if both nodes are NIL, compare data, recurse on children

2. **`isSimilar(BST<T> tree1, BST<T> tree2)`** - Topological similarity
   - Must be implemented with **recursion only**
   - Ignore data content; check only tree shape (same branching structure)
   - Pattern: both NIL → similar; one NIL and other not → not similar; recurse on both subtrees

3. **`orderStatistic(BST<T> tree, int k)`** - K-th order statistic
   - Must be implemented with **recursion only**
   - K-th smallest element (k=1 is minimum, k=N is maximum)
   - Use in-order traversal logic; count and return k-th element visited
   - Return `null` if k is out of bounds

**Critical Restrictions (enforced by interface):**
- ❌ No auxiliary data structures (Lists, Arrays, Sets)
- ❌ No library methods (Java Collections, Streams, etc.)
- ❌ No transformation to array/list and back
- ❌ All helper methods must be in `SimpleBSTManipulationImpl` class only

---

## Development Workflow & Build Commands

### Build & Test (Maven)
```bash
mvn clean compile              # Compile all sources
mvn test                       # Run unit tests (StudentBSTTest)
mvn install                    # Build + run tests + create submission JAR
```

### Key Test File
- `src/test/java/adt/bst/StudentBSTTest.java` - 156 lines of test cases
  - Tests initialization, insert, min/max, successor/predecessor, traversals
  - Pattern: Use `@Before` setUp(), `@Test` methods with JUnit 4 assertions
  - Reference tests to understand expected behavior

### Submission
The `pom.xml` includes `leda-compactor-tool` plugin that creates a submission ZIP at:
- `target/124211085.zip` (after `mvn install`)

---

## Code Patterns & Conventions

### 1. **Recursive Algorithm Pattern**
All tree traversals use tail recursion with private helper methods:
```java
public BSTNode<T> maximum() {
    return achaMaximo(this.root);  // public wrapper calls private recursive helper
}
private BSTNode<T> achaMaximo(BSTNode<T> no) {  // recursive implementation
    if (no.getData() != null) {
        // ... recurse
    }
    return result;
}
```

### 2. **NIL Node Handling**
- Empty tree = root with `data == null`
- Leaf node = node with left/right children having `data == null`
- Always check `no.getData() != null` before recursing; never assume valid data

### 3. **Type Safety & Generics**
- Use `T extends Comparable<T>` for all generic classes
- Cast children: `(BSTNode<T>) node.getLeft()` - This is intentional; BTNode interface returns `BTNode<T>`, not `BSTNode<T>`
- Never mix raw types

### 4. **Comparison in BST**
- Use `.compareTo()` for BST property checks: `element.compareTo(node.getData()) < 0` → go left
- Equality is via `.equals()` for searching

---

## Common Implementation Gotchas

1. **Recursion must bottom out correctly**
   - Base case: `if (node.getData() == null) return ...`
   - Infinite loop risk: forgetting to check NIL nodes

2. **Order-statistic counting**
   - In-order traversal visits nodes in sorted order
   - Maintain a counter; return when counter == k
   - Must handle k > tree size → return null

3. **Tree equality vs. similarity**
   - `equals`: Both structure AND values must match
   - `isSimilar`: Only structure (topology) matters; values ignored

4. **No null insertion**
   - `insert(null)` is silently rejected by BSTImpl
   - Don't assume every tree node has data; check with `.isEmpty()`

---

## Testing & Validation

- Run `StudentBSTTest` first to verify BST implementation is correct
- Then implement & test each method in `SimpleBSTManipulation` separately
- Use small tree examples (3-5 nodes) to trace recursion manually before running full test
- Watch for stack overflow: deep recursion on unbalanced trees (but this is acceptable for assignment)

---

## File Structure Reference

```
src/main/java/adt/
├── bt/                          # Base binary tree package
│   ├── BT.java                 # BT interface (read-only)
│   └── BTNode.java             # Generic tree node (read-only)
└── bst/                        # Binary search tree package
    ├── BST.java                # BST interface (read-only)
    ├── BSTImpl.java             # Core BST implementation (read-only, 284 lines)
    ├── BSTNode.java            # BST node with Builder pattern (read-only)
    ├── SimpleBSTManipulation.java       # Interface (read-only)
    ├── SimpleBSTManipulationImpl.java    # ✏️ ONLY FILE TO EDIT
    └── extended/
        ├── FloorCeilBST.java           # Extension interface (read-only)
        └── FloorCeilBSTImpl.java        # Extension impl (read-only)
```
