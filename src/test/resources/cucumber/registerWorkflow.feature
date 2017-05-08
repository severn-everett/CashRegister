Feature: Register
    As a user
    I want to use a register
    So that I can determine what monetary amount to get in change

    Scenario Outline: Get USD change
        Given I have a register using configuration "normalUSDConfig.yml"
        When I request <amt> in change
        Then I should get the following monetary amounts:
            | Twenty-Dollar Bills   | Ten-Dollar Bills   | Five-Dollar Bills   | One-Dollar Bills   | Quarters   | Dimes   | Nickels   | Pennies   |
            | <Twenty-Dollar Bills> | <Ten-Dollar Bills> | <Five-Dollar Bills> | <One-Dollar Bills> | <Quarters> | <Dimes> | <Nickels> | <Pennies> |

        Examples:
            | amt    | Twenty-Dollar Bills | Ten-Dollar Bills | Five-Dollar Bills | One-Dollar Bills | Quarters | Dimes | Nickels | Pennies |
            | $13.87 |                   0 |                1 |                 0 |                3 |        3 |     1 |       0 |       2 |
            | $8.02  |                   0 |                0 |                 1 |                3 |        0 |     0 |       0 |       2 |
            | $55.55 |                   2 |                1 |                 1 |                0 |        2 |     0 |       1 |       0 |

    Scenario Outline: Get CAD change
        Given I have a register using configuration "normalCADConfig.yml"
        When I request <amt> in change
        Then I should get the following monetary amounts:
            | Twenty-Dollar Bills   | Ten-Dollar Bills   | Five-Dollar Bills   | Two-Dollar Coins   | One-Dollar Coins   | Quarters   | Dimes   | Nickels   | Pennies   |
            | <Twenty-Dollar Bills> | <Ten-Dollar Bills> | <Five-Dollar Bills> | <Two-Dollar Coins> | <One-Dollar Coins> | <Quarters> | <Dimes> | <Nickels> | <Pennies> |

        Examples:
            | amt    | Twenty-Dollar Bills | Ten-Dollar Bills | Five-Dollar Bills | Two-Dollar Coins | One-Dollar Coins | Quarters | Dimes | Nickels | Pennies |
            | $13.87 |                   0 |                1 |                 0 |                1 |                1 |        3 |     1 |       0 |       2 |
            | $8.02  |                   0 |                0 |                 1 |                1 |                1 |        0 |     0 |       0 |       2 |
            | $55.55 |                   2 |                1 |                 1 |                0 |                0 |        2 |     0 |       1 |       0 |

    Scenario: Out Of Money
        Given I have a register using configuration "limitedMoney.yml"
        When I try to request $42.50 in change
        Then I should get an instance of "OutOfMoneyException"
