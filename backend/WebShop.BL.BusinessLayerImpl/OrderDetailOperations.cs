using System.Linq;
using WebShop.DL.DataLayerContract;
using WebShop.DL.DataLayerContract.Entities;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerImpl
{
    public class OrderDetailOperations : IOrderDetailOperations
    {
        private IDataAccess<OrderDetail> _orderDetailDataAccess;

        public OrderDetailOperations(IDataAccess<OrderDetail> orederDetailDataAccess)
        {
            _orderDetailDataAccess = orederDetailDataAccess;
        }

        public void Create(OrderDetailsDTO orderDetail)
        {
            _orderDetailDataAccess.Add(new OrderDetail
            {
                ProductId = orderDetail.ProductId,
                OrderId = orderDetail.OrderId,
                Quantity = orderDetail.Quantity
            });
        }

        public OrderDetailsDTO Get(int orderDetailId)
        {
            var orderDetail = _orderDetailDataAccess.Read().
                Single(x => x.OrderId == orderDetailId);
                
            return new OrderDetailsDTO()
            {
                ProductId = orderDetail.ProductId,
                OrderId = orderDetail.OrderId,
                Quantity = orderDetail.Quantity
            };
        }
    }
}
