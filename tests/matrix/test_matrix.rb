require 'test/unit'
require_relative '../../matrix/matrix'

class MatrixTest < Test::Unit::TestCase
  def create_matrix1
    Matrix.new(
      city_count: 5,
      machine_count: 3,
      roads: [
        {between: [2,1], time: 8},
        {between: [1,0], time: 5},
        {between: [2,4], time: 5},
        {between: [1,3], time: 4},
      ],
      cities_with_machines: [2,4,0],
    )
  end

  def create_matrix2
    Matrix.new(
      city_count: 5,
      machine_count: 3,
      roads: [
        {between: [2,1], time: 8},
        {between: [1,0], time: 5},
        {between: [2,4], time: 5},
        {between: [1,3], time: 4},
        {between: [0,4], time: 3},
      ],
      cities_with_machines: [2,4,0],
    )
  end

  def test_initialize
    matrix= create_matrix1
    #initializes cities list
    assert_equal(matrix.cities.map {|c| c[:id]}.sort, [0,1,2,3,4])

    #cities are linked with time and enabled flag
    city1= matrix.cities.select {|c| c[:id]==1}[0]
    assert_equal(city1[:links].map {|lc| lc[:linked_city][:id]}.sort, [0,2,3])
    assert_equal(city1[:links].map {|lc| lc[:time]}.sort, [4,5,8])
    assert_equal(city1[:links].map {|lc| lc[:enabled]}, [true]*3)

    #cities are linked 2 ways
    city2= matrix.cities.select {|c| c[:id]==2}[0]
    city4= matrix.cities.select {|c| c[:id]==4}[0]
    assert_equal(city2[:links].map {|l| l[:linked_city][:id]}.include?(city4[:id]), true)
    assert_equal(city4[:links].map {|l| l[:linked_city][:id]}.include?(city2[:id]), true)
  end

  def test_cities_with_machines
    matrix= create_matrix1
    assert_equal(matrix.cities_with_machines.map {|cm| cm[:id]}.sort, [0,2,4])
  end

  def test_routes_between_cities
    matrix2= create_matrix2
    city0= matrix2.cities.select {|c| c[:id]==0}[0]
    city2= matrix2.cities.select {|c| c[:id]==2}[0]

    routes02= matrix2.routes_between_cities(city0, city2)
    # [
    #   [
    #     {
    #       linked_city: city0,
    #       time: 0,
    #       enabled: true,
    #     },
    #     {
    #       linked_city: city1,
    #       time: 5,
    #       enabled: true,
    #     },
    #     {
    #       linked_city: city2,
    #       time: 8,
    #       enabled: true,
    #     },
    #   ],
    #
    #   [
    #     {
    #       linked_city: city0,
    #       time: 0,
    #       enabled: true,
    #     },
    #     {
    #       linked_city: city4,
    #       time: 5,
    #       enabled: true,
    #     },
    #     {
    #       linked_city: city2,
    #       time: 8,
    #       enabled: true,
    #     },
    #   ],
    # ]

    assert_equal(routes02.respond_to?(:each), true)
    assert_equal(routes02.length, 2)
    assert_equal(routes02[0].length, 3)
    assert_equal(routes02[1].length, 3)

    assert_equal(routes02[0].map {|link| link[:linked_city][:id]}, [0,1,2])
    assert_equal(routes02[0].map {|road| road[:time]}.sort, [0,5,8])

    assert_equal(routes02[1].map {|link| link[:linked_city][:id]}, [0,4,2])
    assert_equal(routes02[1].map {|road| road[:time]}.sort, [0,3,5])

  end

  # def test_routes_between_cities_with_machines
  #   matrix2= create_matrix2
  #   cities_with_machines= matrix2.cities_with_machines
  #   routes_between_cities_with_machines= matrix2.routes_between_cities_with_machines

  #   assert_equal(routes_between_cities_with_machines.length, 4)
  # end
end
