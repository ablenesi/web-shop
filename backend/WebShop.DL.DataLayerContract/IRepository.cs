using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.DL.DataLayerContract
{
    public interface IRepository
    {
        IDataAccess<Product> ProductRepository { get; }
        IDataAccess<Category> CategoryRepository { get; }
        IDataAccess<Order> OrderRepository { get; }
        IDataAccess<OrderDetail> OrderDetailRepository { get; }

        void SaveChanges();
    }
}
