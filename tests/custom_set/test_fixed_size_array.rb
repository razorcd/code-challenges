require 'test/unit'
require_relative '../../custom_set/fixed_size_array'

class FixedSizeArrayTest < Test::Unit::TestCase
  def fixed_array_of_5
    FixedSizeArray.new 5
  end

  def test_initialize
    assert_equal(defined?(fixed_array_of_5).!.!, true)
  end

  def test_set_and_get
    arr= fixed_array_of_5
    arr[0]= 1
    arr[4]= "a"
    assert_equal(arr[0], 1)
    assert_equal(arr[4], "a")
  end

  def test_length
    arr= fixed_array_of_5

    assert_equal(arr.length, 5)
  end

  def test_inspect
    arr= fixed_array_of_5

    arr[0]= 1
    arr[4]= "a"

    expect_arr= [1, nil, nil, nil, "a"]
    for i in (0..arr.length-1)
      assert_equal(arr[i], expect_arr[i])
    end
  end

  def test_accessing_out_of_bound_memory
    arr= fixed_array_of_5

    assert_raise do
      a[10]
    end

    assert_raise do
      a[10]= "data"
    end
  end
end
