using WebShop.DL.DataLayerContract;
using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.DL.EFDataLayer
{
    public class EFRepository : IRepository
    {
        private WebShopContext _ctx;
        private IDataAccess<Product> _productDataAccess;
        private IDataAccess<Category> _categoryDataAccess;
        private IDataAccess<Order> _orderDataAccess;
        private IDataAccess<OrderDetail> _orderDetailsDataAccess;

        public EFRepository(WebShopContext ctx)
        {
            _ctx = ctx;
        }


        public IDataAccess<Product> ProductRepository
        {
            get 
            {
                if (_productDataAccess == null)
                {
                    _productDataAccess = new EFDataAccess<Product>(_ctx);
                }

                return _productDataAccess;
            }
        }

        public IDataAccess<Category> CategoryRepository
        {
            get
            {
                if (_categoryDataAccess == null)
                {
                    _categoryDataAccess = new EFDataAccess<Category>(_ctx);
                }

                return _categoryDataAccess;
            }
        }

        public IDataAccess<Order> OrderRepository
        {
            get
            {
                if (_orderDataAccess == null)
                {
                    _orderDataAccess = new EFDataAccess<Order>(_ctx);
                }

                return _orderDataAccess;
            }
        }

        public IDataAccess<OrderDetail> OrderDetailRepository
        {
            get
            {
                if (_orderDetailsDataAccess == null)
                {
                    _orderDetailsDataAccess = new EFDataAccess<OrderDetail>(_ctx);
                }

                return _orderDetailsDataAccess;
            }
        }

        public void SaveChanges()
        {
            _ctx.SaveChanges();
        }
    }
}
